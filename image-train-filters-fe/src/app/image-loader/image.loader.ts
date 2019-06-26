import {Component, OnInit} from '@angular/core';
import {FileUploader} from 'ng2-file-upload/ng2-file-upload';
import {DomSanitizer} from "@angular/platform-browser";
import {Command} from "./command.types";
import {NbTabComponent} from "@nebular/theme";
import {ImageLoaderContourComponent} from "./image-loader-contour/image.loader.contour";
import {ImageLoaderKuwaharaComponent} from "./image-loader-kuwahara/image.loader.kuwahara";
import {ImageLoaderChartizateComponent} from "./image-loader-chartizate/image.loader.chartizate";

const URL: string = '/api/images';

class ImageChangeEvent extends Event {
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
    fileUrl: any;
    commands: Command[];
    private currentTab: NbTabComponent;
    private ilcontour: ImageLoaderContourComponent;
    private ilkuwahara: ImageLoaderKuwaharaComponent;
    private ilchartizate: ImageLoaderChartizateComponent;

    constructor(public domSanitizer: DomSanitizer) {
        this.loading = false;
    }

    ngOnInit() {
        this.uploader = new FileUploader({
            url: URL,
            method: 'post',
            itemAlias: 'filename',
            disableMultipart: false,
            queueLimit: 1,
            maxFileSize: 100000000,
        });
        this.uploader.onBuildItemForm = (fileItem: any, form: any) => {
            switch (this.currentTab.tabTitle) {
                case "Contour":
                    this.commands = this.ilcontour.getConfiguration();
                    break;
                case "Kuwahara":
                    this.commands = this.ilkuwahara.getConfiguration();
                    break;
                case "Chartizate":
                    this.commands = this.ilchartizate.getConfiguration();
                    break;

            }
            form.append('commands', JSON.stringify({commands: this.commands}));
        };
        this.uploader.onCompleteItem = (item: any, response: any, status: any, headers: any) => {
            this.removeAllElementsFromQueue();
            if (status != 200) {
                this.errorStatus = status;
                this.errorText = response;
                if (status == 503) {
                    this.adviceText = "Your picture is either too big (>100Mb) or it's complexion is too heavy for the current algorithm implementation";
                }
            } else {
                let generatedImage = this.domSanitizer.bypassSecurityTrustUrl("data:image/png;base64, " + response);
                this.fileUrl = generatedImage;
                this.imageToShow = generatedImage;
            }
            this.loading = false;
        };
    }

    imageChanged($event?: Event) {
        this.resetAllControls();
        if ($event) {
            let file = (<ImageChangeEvent>$event).target.files[0];
            if (file) {
                this.filename = file.name;
            } else {
                this.filename = null;
                this.removeAllElementsFromQueue();
            }
        }
        this.imageToShow = null;
    }

    resetAll(){
        this.removeAllElementsFromQueue();
        this.resetAllControls();
    }

    loadImage() {
        this.resetAllControls();
        this.loading = true;
        this.uploader.uploadAll();
    }

    tabChanged(tab: NbTabComponent, ilcontour: ImageLoaderContourComponent, ilkuwahara: ImageLoaderKuwaharaComponent, ilchartizate: ImageLoaderChartizateComponent) {
        this.commands = [];
        this.currentTab = tab;
        this.ilcontour = ilcontour;
        this.ilkuwahara = ilkuwahara;
        this.ilchartizate = ilchartizate;
    }

    private resetAllControls() {
        this.adviceText = null;
        this.errorStatus = null;
        this.errorText = null;
        this.filename = null;
    }

    private removeAllElementsFromQueue() {
        this.uploader.cancelAll();
        this.uploader.clearQueue();
    }
}
