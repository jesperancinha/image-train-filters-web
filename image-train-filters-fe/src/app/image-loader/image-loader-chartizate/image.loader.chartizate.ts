import {Component, OnInit} from '@angular/core';
import {NbComponentSize} from "@nebular/theme";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Options} from "ng5-slider";
import {ItfChartizateCommand} from "../../command-types/itf-chartizate-command";
import {Command} from "../../command-types/command";

@Component({
    selector: 'image-loader-chartizate',
    styleUrls: ['./image.loader.chartizate.scss'],
    templateUrl: './image.loader.chartizate.html',
})
export class ImageLoaderChartizateComponent implements OnInit {

    public loading: boolean;
    public commands: ItfChartizateCommand[] = [];
    public itfChartizateCommand: ItfChartizateCommand = new ItfChartizateCommand();
    public unicodes: NbComponentSize[] = [];
    public fonts: NbComponentSize[] = [];
    public options: Options = {
        floor: 0,
        ceil: 100
    };

    private headers = new HttpHeaders({'Content-Type': 'application/json'});
    private errorText: any;

    constructor(private httpClient: HttpClient) {
        this.loading = false;
        this.itfChartizateCommand.bgColor = "#000000";
        this.itfChartizateCommand.density = 50;
        this.itfChartizateCommand.range = 10;
        this.itfChartizateCommand.font = "Arial";
        this.itfChartizateCommand.fontSize = 5;
        this.itfChartizateCommand.unicode = "ARABIC";
        this.commands.push(this.itfChartizateCommand);
    }

    public ngOnInit(): void {
        this.httpClient.get<any>('/api/listings/unicodes', {headers: this.headers}).toPromise()
            .then(value => {
                this.unicodes = value.content.sort();
            })
            .catch(fail => {
                this.errorText = fail;
            });
        this.httpClient.get<any>('/api/listings/fonts', {headers: this.headers}).toPromise()
            .then(value => {
                this.fonts = value.content.sort();
            })
            .catch(fail => {
                this.errorText = fail;
            });
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
