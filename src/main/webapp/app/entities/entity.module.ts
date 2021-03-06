import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'program',
        loadChildren: () => import('./program/program.module').then(m => m.GnsggmsProgramModule),
      },
      {
        path: 'task',
        loadChildren: () => import('./task/task.module').then(m => m.GnsggmsTaskModule),
      },
      {
        path: 'sevadar',
        loadChildren: () => import('./sevadar/sevadar.module').then(m => m.GnsggmsSevadarModule),
      },
      {
        path: 'student',
        loadChildren: () => import('./student/student.module').then(m => m.GnsggmsStudentModule),
      },
      {
        path: 'charge',
        loadChildren: () => import('./charge/charge.module').then(m => m.GnsggmsChargeModule),
      },
      {
        path: 'applied-charge',
        loadChildren: () => import('./applied-charge/applied-charge.module').then(m => m.GnsggmsAppliedChargeModule),
      },
      {
        path: 'as-path',
        loadChildren: () => import('./as-path/as-path.module').then(m => m.GnsggmsASPathModule),
      },
      {
        path: 'p-roul',
        loadChildren: () => import('./p-roul/p-roul.module').then(m => m.GnsggmsPRoulModule),
      },
      ,
      {
        path: 'expense',
        loadChildren: () => import('./expense/expense.module').then(m => m.GnsggmsExpenseModule),
      },
      {
        path: 'revenue',
        loadChildren: () => import('./revenue/revenue.module').then(m => m.GnsggmsRevenueModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class GnsggmsEntityModule {}
