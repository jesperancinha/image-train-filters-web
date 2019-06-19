import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { ImageComponent } from './image-loader/image.loader';

import { FileSelectDirective } from 'ng2-file-upload';

@NgModule({
  imports:      [ BrowserModule, FormsModule ],
  declarations: [ AppComponent, ImageComponent, FileSelectDirective ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
