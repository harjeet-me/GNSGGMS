import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GnsggmsSharedModule } from 'app/shared/shared.module';
import { SevadarComponent } from './sevadar.component';
import { SevadarDetailComponent } from './sevadar-detail.component';
import { SevadarUpdateComponent } from './sevadar-update.component';
import { SevadarDeleteDialogComponent } from './sevadar-delete-dialog.component';
import { sevadarRoute } from './sevadar.route';

@NgModule({
  imports: [GnsggmsSharedModule, RouterModule.forChild(sevadarRoute)],
  declarations: [SevadarComponent, SevadarDetailComponent, SevadarUpdateComponent, SevadarDeleteDialogComponent],
  entryComponents: [SevadarDeleteDialogComponent],
})
export class GnsggmsSevadarModule {}
