package validation;

import database.DataBaseOperations;

public class DBInfoRenovate {
	public static void delOldTickets() {
		String proc = "{call dbo.deleteOldTicktes}";
		DataBaseOperations.executeProcedure(proc);
	}
}
