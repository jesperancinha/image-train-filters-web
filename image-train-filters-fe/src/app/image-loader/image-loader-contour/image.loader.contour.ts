import {Component, OnInit} from '@angular/core';
import {FileUploader} from 'ng2-file-upload/ng2-file-upload';
import {DomSanitizer} from "@angular/platform-browser";

const URL: string = '/api/images';

class ImageChangeEvent extends Event {
    target: any;
    files: any;
}

@Component({
    selector: 'image-loader-contour',
    templateUrl: './image.loader.contour.html',
    styleUrls: ['./image.loader.contour.css']
})
export class ImageLoaderContourComponent implements OnInit {
    loading: boolean;
    bgColor: any;
    lineColor: any;

    constructor() {
        this.loading = false;
    }

    ngOnInit() {
    }

}
