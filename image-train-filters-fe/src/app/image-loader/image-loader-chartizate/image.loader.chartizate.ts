import {Component, OnInit} from '@angular/core';
import {NbComponentSize} from "@nebular/theme";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Options} from "ng5-slider";
import {ItfChartizateCommand} from "../../command-types/itf-chartizate-command";
import {Command} from "../../command-types/command";

const DEFAULT_BACKGROUND_COLOR = "#000000";
const DEFAULT_DENSITY = 50;
const DEFAULT_RANGE_PERCENTAGE = 10;
const DEFAULT_FONT = "Arial";
const DEFAULT_FONT_SIZE = 5;
const DEFAULT_UNICODE = "LATIN_EXTENDED_A";

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
        ceil: 100,
        floor: 0,
    };
    private headers = new HttpHeaders({'Content-Type': 'application/json'});

    private errorText: string;

    constructor(private httpClient: HttpClient) {
        this.loading = false;
        this.itfChartizateCommand.bgColor = DEFAULT_BACKGROUND_COLOR;
        this.itfChartizateCommand.density = DEFAULT_DENSITY;
        this.itfChartizateCommand.range = DEFAULT_RANGE_PERCENTAGE;
        this.itfChartizateCommand.font = DEFAULT_FONT;
        this.itfChartizateCommand.fontSize = DEFAULT_FONT_SIZE;
        this.itfChartizateCommand.unicode = DEFAULT_UNICODE;
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

    public getConfiguration(): Command[] {
        return this.commands.map(command => {
            const newCommand = new Command();
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
