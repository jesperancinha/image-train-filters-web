import {Component, OnInit} from '@angular/core';
import {ItfChartizateCommand, ItfCommand} from "../command.types";

@Component({
    selector: 'image-loader-chartizate',
    templateUrl: './image.loader.chartizate.html',
    styleUrls: ['./image.loader.chartizate.css']
})
export class ImageLoaderChartizateComponent implements OnInit {

    loading: boolean;
    commands: ItfCommand[] = [];
    itfChartizateCommand: ItfChartizateCommand = new ItfChartizateCommand();

    constructor() {
        this.loading = false;
        this.commands.push(this.itfChartizateCommand);
    }

    ngOnInit() {
    }
}
