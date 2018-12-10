import { Routes } from '@angular/router';
import { SectionComponent } from './section/section.component';
import { TeacherComponent } from './teacher/teacher.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import { SubjectComponent } from './subject/subject.component';
import { SemesterComponent } from './semester/semester.component';
import { ClassComponent } from './class/class.component';
import { HomeComponent } from './home/home.component'
import { HolidaysComponent } from './holidays/holidays.component';
import { TimetableComponent } from './timetable/timetable.component';
export const routes: Routes = [
    {
        path: '',
        children: [
            {
                path: 'classes',
                component: ClassComponent
            },
            {
                path: 'sections',
                component: SectionComponent
            },
            {
                path: 'teachers',
                component: TeacherComponent
            },
            {
                path: 'subjects',
                component: SubjectComponent
            },
            {
                path: 'semester',
                component: SemesterComponent
            },
            {
                path: 'holidays',
                component: HolidaysComponent
            },
            {
                path: 'timetable',
                component: TimetableComponent
            }
        ],
        component: HomeComponent
    }, {
        path: '**',
        component: PagenotfoundComponent
    }
];

