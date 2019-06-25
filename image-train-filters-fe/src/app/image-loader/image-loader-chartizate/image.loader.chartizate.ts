import {Component, OnInit} from '@angular/core';

const URL: string = '/api/images';

@Component({
    selector: 'image-loader-chartizate',
    templateUrl: './image.loader.chartizate.html',
    styleUrls: ['./image.loader.chartizate.css']
})
export class ImageLoaderChartizateComponent implements OnInit {

    loading: boolean;
    bgColor: any;

    constructor() {
        this.loading = false;
    }

    ngOnInit() {
    }
}
