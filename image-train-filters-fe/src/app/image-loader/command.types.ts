export class ItfCommand {
}

export class Setting {
    name: string;
    value: string;
}

export class ItfChartizateCommand extends ItfCommand {
    bgColor: string;
    density: number;
    range: number;
    font: string;
    fontSize: string;
    unicode: string;

}

export class ItfContourCommand extends ItfCommand {
    bgColor: string;
    lnColor: string;
    diffThreshold: number;
    radius: number;
}

export class ItfKuwaharaCommand extends ItfCommand {
    squareSize: number;
    iterations: number;
}