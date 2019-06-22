import {Component, OnInit} from '@angular/core';
import {FileUploader} from 'ng2-file-upload/ng2-file-upload';
import {DomSanitizer} from "@angular/platform-browser";

const URL: string = '/api/images';

class ImageChangeEvent {
    target: any;
    files: any;
}

@Component({
    selector: 'image-loader',
    templateUrl: './image.loader.html',
    styleUrls: ['./image.loader.css']
})
export class ImageComponent implements OnInit {
    public uploader: FileUploader;

    imageToShow: any;
    filename: String;
    loading: boolean;
    errorText: String;
    errorStatus: String;
    adviceText: String;

    constructor(public domSanitizer: DomSanitizer) {
        this.loading = false;
    }

    ngOnInit() {
        this.uploader = new FileUploader({
            url: URL,
            method: 'post',
            itemAlias: 'filename',
            disableMultipart: false//,
        });
        this.uploader.onBuildItemForm = (fileItem: any, form: any) => {
            form.append('commands', "{ \"commands\": [ { \"filter\": \"imageKuwahara\", \"settings\": [ { \"name\": \"square-size\", \"value\": \"2\"}, { \"name\": \"iterations\", \"value\": \"2\"} ]}, { \"filter\": \"imageContour\", \"settings\": [ { \"name\": \"bgColor\", \"value\": \"0xFFFFFF\"}, { \"name\": \"lnColor\", \"value\": \"0x000000\"}, { \"name\": \"diffThreshold\", \"value\": \"800000\"}, { \"name\": \"radius\", \"value\": \"2\"} ]} ] }");
        };
        this.uploader.onCompleteItem = (item: any, response: any, status: any, headers: any) => {
            if (status != 200) {
                this.errorStatus = status;
                this.errorText = response;
                if(status == 503){
                    this.adviceText = "Your picture is either too big (>100Mb) or it's complexion is too heavy for the current algorithm implementation";
                }
            } else {
                this.imageToShow = this.domSanitizer.bypassSecurityTrustUrl("data:image/png;base64, " + response);
            }
            this.loading = false;
            this.uploader.cancelAll();
        };
    }

    imageChanged($event?: ImageChangeEvent) {
        if ($event) {
            this.filename = $event.target.files[0].name;
        }
        this.imageToShow = null;
    }

    loadImage() {
        this.adviceText = null;
        this.errorStatus = null;
        this.errorText = null;
        this.loading = true;
        this.uploader.uploadAll();
    }
}
