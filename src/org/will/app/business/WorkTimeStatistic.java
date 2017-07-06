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

		// �ۼ�ԭʼ������
		e.setSprintInitialTime(e.getSprintInitialTime() + Float.parseFloat(task.getInitial()));
		// �ۼ�ʣ�๤����
		e.setSprintRemainingTime(e.getSprintRemainingTime() + Float.parseFloat(task.getEstimation()));
		// ������Ч�������б�
		e.getTasks().add(task);

	}

	public List<Employee> execute() {

		List<Task> tasks = getTasksForCheckedProjects();
		for (Task task : tasks) {
			String id = null;
			// task������
			if (task.getResponsible() != null) {
				id = task.getResponsible().getId();
			}

			if (id != null) {

				Employee e = this.findEmployee(id);
				// ����task������ͳ����Ա�ķ�Χ��
				if (e != null) {
					// todo
					if (task.getState().equals("0")) {
						// ������������
						if (task.getEstimation() != null) {
							// �ۼ�ԭʼ������,��task��Ϊtodoʱ����¼��ʼ����������estimation�ֶ�
							e.setSprintInitialTime(e.getSprintInitialTime() + Float.parseFloat(task.getEstimation()));
							// �ۼ�ʣ�๤����
							e.setSprintRemainingTime(
									e.getSprintRemainingTime() + Float.parseFloat(task.getEstimation()));
							// ������Ч�������б�
							e.getTasks().add(task);
						}

					}
					// in progress ������������
					else if (task.getState().equals("1")) {
						// ���쳣������inprogress���ڵ�task�������й�����
						if (task.getEstimation() == null) {
							// ��¼���쳣task�������˹�����
							e.getErrorTask().add(task);
						}
						// ���쳣������й���������������Ĺ�����û����Todo��ʱ��������������inprogress�ġ���ֱ�Ӳ��Ĺ�������
						if (task.getEstimation() != null && task.getInitial() == null) {
							// ��¼���쳣task�������˹�����
							e.getErrorTask().add(task);
						}
						// �������������ͳ��
						if (task.getEstimation() != null && task.getInitial() != null) {
							count(e, task);
						}

					}
					// done
					else {
						// �������������done��task estiamtion��initical������Ϊnull
						if (task.getEstimation() != null && task.getInitial() != null) {
							count(e, task);
						} else // �쳣�������¼��task���˹��޸�
						{
							e.getErrorTask().add(task);
						}

					}
					// �ۼ�task����
					e.setSprintTotalTask(e.getSprintTotalTask() + 1);

				}
			}
		}

		List<Employee> newEmployees = new ArrayList<Employee>();

		for (Employee e : employees) {
			// �����ͬ�µ�task����Ϊ0��˵�����������Ŀ���޳���
			if (e.getSprintTotalTask() == 0) {
				continue;
			}
			// ��ͳ�����˼ƻ���ɵĹ�����
			e.setSprintFinishedTime(e.getSprintInitialTime() - e.getSprintRemainingTime());
			newEmployees.add(e);
		}

		employees = newEmployees;

		// employees.sort(new SortByGroup());

		employees.sort(new SortByWorkTime());
		// ����ɰٷֱ�
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

	// ��������task
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
