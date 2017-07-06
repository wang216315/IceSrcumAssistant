package org.will.app.business;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.will.app.db.DBServiceProvider;
import org.will.app.model.UploadFile;
import org.will.app.model.User;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class InputEmployeeIDRelationship {

	public InputEmployeeIDRelationship() {

	}

	public boolean executeInsertSingleUser(User user) {
		try {
			
			SessionFactory sf = DBServiceProvider.getSessionFactoryVersion5();
			Session sess = sf.openSession();
			Transaction tx = sess.beginTransaction();
			sess.save(user);
			tx.commit();
			sess.close();
			DBServiceProvider.closeSession();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public void execute(UploadFile uploadFile) {

		save(filter(getUserFromExcel2003(uploadFile)));

		DBServiceProvider.closeSession();

	}

	private void save(List<User> users) {
		SessionFactory sf = DBServiceProvider.getSessionFactoryVersion5();
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		int i = 0;
		for (User u : users) {
			sess.save(u);

			if (i % 20 == 0) {
				sess.flush();
				sess.clear();
			}
		}

		tx.commit();
		sess.close();

	}

	private List<User> filter(List<User> users) {

		List<User> newUsers = new ArrayList<User>();

		List<User> p = User.getAllUsers();

		for (User u : users) {
			User user = User.findUser(p, u.getIsuid().toString());
			// 如果在数据库里没有找到该user，说明是一个新User，需要插入
			if (user == null) {
				newUsers.add(u);
			}
		}

		return newUsers;

	}

	private List<User> getUserFromExcel2003(UploadFile uploadFile) {

		InputStream is = null;
		jxl.Workbook readwb = null;
		List<User> users = new ArrayList<User>();

		try {

			is = new FileInputStream(uploadFile.getFile());
			readwb = Workbook.getWorkbook(is);
			// 获取第一张sheet
			Sheet readsheet = readwb.getSheet(0);
			// int rsColumnsCount = readsheet.getColumns();
			int reRowsCount = readsheet.getRows();

			for (int row = 1; row < reRowsCount; row++) {
				User user = new User();
				Cell cell = readsheet.getCell(0, row);

				user.setIsuid(Integer.parseInt(cell.getContents()));
				cell = readsheet.getCell(1, row);

				user.setName(cell.getContents());

				cell = readsheet.getCell(2, row);

				user.setActor(cell.getContents());

				users.add(user);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();

				}
				if (readwb != null) {
					readwb.close();
				}
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
		return users;
	}

}
