export class Constants {
  baseUrl = 'http://localhost:8080/api/';
  apiUrls = {
    semester: 'semester',
    class: 'class',
    section: 'section',
    subject: 'subject',
  };
  routes = [
    {
      name: 'Semesters',
      path: '/semester'
    },{
      name: 'Classes',
      path: '/classes'
    }, {
    name: 'Subjects',
    path: '/subjects'
  },
{
  name: 'Time Table',
  path: '/timetable'
}];
}
