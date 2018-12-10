Completed Api's

Semester Api's
- Add/Remove/Fetch All/Delete/Update

Holiday Api's
- Add/remove/Fetch Holidays by semesterId/Fetch Working Days by semesterId

Class Api's
- Add/remove/Fetch All/Fetch particular class

Section Api's
- Add/remove/Fetch all by classId/map subjects to section / get subjects by section / delete subjects by section

Subject Api's
-Add/remove/Fetch All/Fetch particular subject

Teacher Api's
- Add/remove/Fetch all by subject id/ fetch all teacher by subjects of all id/fetch all teacher by pool of all types

TimeTable Api's
- Create timetable by section / class / all classes
- Update timetable by section / class / all classes
- Remove timetable by section / class / all classes
- View timetable by particular class and its section on a particular date , timetable by particular teacher on a particular date

Algorithm to generate timetable
- Basically timetable is generated on the fly. 
- Initially while creating timetable, only teachers are mapped to a composite primary key(section id and subject id)
- While creating timetable, algorithm is designed such that every teacher(associated to a particular subject) is assigned to timetable.
- Considering dates, periods are designed dynamically according to a day of the week.

Steps to build the application
	-Open the project in STS/Eclipse and go to application.properties and set up the SQL configuration.
		spring.datasource.url=jdbc:mysql://localhost:3306/timetable (change your sql port on which the server is set up)
		spring.datasource.username  (sql username for the server)
		spring.datasource.password 	(sql password for the server)
	- Run the application as Spring boot app and port in configured at 8080.
	- Angular is configured at port 4201. Simply run the project by command - ng serve.