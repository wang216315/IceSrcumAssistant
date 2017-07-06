package org.will.app.business;

import java.util.ArrayList;

import java.util.Comparator;

import java.util.List;

import org.will.app.model.Employee;

import org.will.app.model.Operator;
import org.will.app.model.Task;
import org.will.app.model.User;

public class WorkTimeStatistic {
	private List<Employee> employees = new ArrayList<Employee>();

	private Operator operator;

	public WorkTimeStatistic(Operator operator) {
		this.operator = operator;
		getEmployeesList();
	}

	private List<Employee> getEmployeesList() {

		List<User> users = User.getAllUsers();

		for (User user : users) {
			Employee employee = new Employee(user.getIsuid().toString(), user.getName(), user.getActor());
			// System.out.println(user.getIsuid().toString()+user.getName()+user.getActor());
			employees.add(employee);
		}

		return employees;
	}

	private Employee findEmployee(String id) {
		for (Employee employee : employees) {
			if (employee.getPid().equals(id)) {
				return employee;
			}
		}
		return null;
	}

	private void count(Employee e, Task task) {

		// 累加原始工作量
		e.setSprintInitialTime(e.getSprintInitialTime() + Float.parseFloat(task.getInitial()));
		// 累加剩余工作量
		e.setSprintRemainingTime(e.getSprintRemainingTime() + Float.parseFloat(task.getEstimation()));
		// 计入有效工作量列表
		e.getTasks().add(task);

	}

	public List<Employee> execute() {

		List<Task> tasks = getTasksForCheckedProjects();
		for (Task task : tasks) {
			String id = null;
			// task有认领
			if (task.getResponsible() != null) {
				id = task.getResponsible().getId();
			}

			if (id != null) {

				Employee e = this.findEmployee(id);
				// 认领task的人在统计人员的范围里
				if (e != null) {
					// todo
					if (task.getState().equals("0")) {
						// 有评估工作量
						if (task.getEstimation() != null) {
							// 累加原始工作量,当task的为todo时，记录初始工作量的是estimation字段
							e.setSprintInitialTime(e.getSprintInitialTime() + Float.parseFloat(task.getEstimation()));
							// 累加剩余工作量
							e.setSprintRemainingTime(
									e.getSprintRemainingTime() + Float.parseFloat(task.getEstimation()));
							// 计入有效工作量列表
							e.getTasks().add(task);
						}

					}
					// in progress 有评估工作量
					else if (task.getState().equals("1")) {
						// 有异常，进入inprogress环节的task都必须有工作量
						if (task.getEstimation() == null) {
							// 记录该异常task，进行人工修正
							e.getErrorTask().add(task);
						}
						// 有异常情况，有工作量，但该任务的工作量没有在Todo的时候评估好在拖入inprogress的。是直接补的工作量。
						if (task.getEstimation() != null && task.getInitial() == null) {
							// 记录该异常task，进行人工修正
							e.getErrorTask().add(task);
						}
						// 正常情况，可以统计
						if (task.getEstimation() != null && task.getInitial() != null) {
							count(e, task);
						}

					}
					// done
					else {
						// 正常情况，进入done的task estiamtion和initical都不能为null
						if (task.getEstimation() != null && task.getInitial() != null) {
							count(e, task);
						} else // 异常情况，记录该task，人工修复
						{
							e.getErrorTask().add(task);
						}

					}
					// 累加task数量
					e.setSprintTotalTask(e.getSprintTotalTask() + 1);

				}
			}
		}

		List<Employee> newEmployees = new ArrayList<Employee>();

		for (Employee e : employees) {
			// 如果该同事的task数量为0，说明不在这个项目，剔除。
			if (e.getSprintTotalTask() == 0) {
				continue;
			}
			// 等统计完了计划完成的工作量
			e.setSprintFinishedTime(e.getSprintInitialTime() - e.getSprintRemainingTime());
			newEmployees.add(e);
		}

		employees = newEmployees;

		// employees.sort(new SortByGroup());

		employees.sort(new SortByWorkTime());
		// 算完成百分比
		countFinishedPercent(employees);

		return employees;

	}

	private void countFinishedPercent(List<Employee> employees) {
		for (Employee e : employees) {
			float percent = e.getSprintFinishedTime() / e.getSprintInitialTime();
			percent = (float) (Math.round(percent * 1000)) / 1000;
			e.setSprintFinishedPercent(percent * 100);
		}
	}

	// 汇总所有task
	private List<Task> getTasksForCheckedProjects() {
		List<Task> targetTasks = new ArrayList<Task>();
		for (String pk : operator.getPkeys()) {
			
				operator.setPkey(pk);
				List<Task> tasks = CommonDataDownload.getAllTasksWithCurrentSprint(operator);
				targetTasks.addAll(tasks);
			

		}

		return targetTasks;
	}

}

class SortByWorkTime implements Comparator<Employee> {

	@Override
	public int compare(Employee x, Employee y) {
		if (x.getSprintInitialTime() > y.getSprintInitialTime()) {
			return 1;
		} else {
			return -1;
		}
	}

}

class SortByGroup implements Comparator<Employee> {
	@Override
	public int compare(Employee x, Employee y) {
		return x.getGroup().compareTo(y.getGroup());
	}

}
