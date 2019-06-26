import {Component, OnInit} from '@angular/core';
import {Command, ItfChartizateCommand} from "../command.types";

@Component({
    selector: 'image-loader-chartizate',
    templateUrl: './image.loader.chartizate.html',
    styleUrls: ['./image.loader.chartizate.css']
})
export class ImageLoaderChartizateComponent implements OnInit {

    loading: boolean;
    commands: ItfChartizateCommand[] = [];
    itfChartizateCommand: ItfChartizateCommand = new ItfChartizateCommand();

    constructor() {
        this.loading = false;
        this.itfChartizateCommand.bgColor = "#000000";
        this.itfChartizateCommand.density = 50;
        this.itfChartizateCommand.range = 10;
        this.itfChartizateCommand.font = "Arial";
        this.itfChartizateCommand.fontSize = 5;
        this.itfChartizateCommand.unicode = "ARABIC";
        this.commands.push(this.itfChartizateCommand);
    }

    ngOnInit() {

    }

    getConfiguration() {
        return this.commands.map(command => {
            let newCommand = new Command();
            newCommand.filter = "imageChartizate";
            newCommand.settings = [];
            newCommand.settings.push({name: "bgColor", value: command.bgColor});
            newCommand.settings.push({name: "densityPer", value: String(command.density)});
            newCommand.settings.push({name: "rangePer", value: String(command.range)});
            newCommand.settings.push({name: "font", value: command.font});
            newCommand.settings.push({name: "fontSize", value: String(command.fontSize)});
            newCommand.settings.push({name: "unicode", value: command.unicode});
            return newCommand;
        });
    }
}
