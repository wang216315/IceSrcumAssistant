package org.will.app.business;


import org.will.app.model.Backlog;
import org.will.app.model.Creator;
import org.will.app.model.Responsible;
import org.will.app.model.Story;
import org.will.app.model.Task;

import net.sf.json.JSONObject;

public class Test
{
	public void oneTest()
	{
		Task task = new Task();
		task.setBlocked("a");
		task.setColor("b");
		Creator css = new Creator();
		css.setId("1");
		task.setCreater(css);
		task.setCreationDate("c");
		task.setDescription("d");
		task.setDoneDate("f");
		task.setEstimation("g");
		task.setId("h");
		task.setInitial("i");
		task.setInProgressDate("j");
		task.setLastUpdated("k");
		task.setName("l");
		task.setNotes("m");
		Story st = new Story();
		st.setId("3");
		task.setParentStory(st);
		task.setParticipants(new String[]{"o"});
		task.setRank("p");
		Backlog bl = new Backlog();
		bl.setId("2");
		task.setBacklog(bl);
		Responsible res = new Responsible();
		res.setId("4");
		task.setResponsible(res);
		task.setState("r");
		task.setTags(new String[]{"s"});
		task.setType("t");
		task.setUid("u");
		
		
		JSONObject jsonObj = JSONObject.fromObject(task);
		System.out.println(jsonObj);
		
		
	}
}
