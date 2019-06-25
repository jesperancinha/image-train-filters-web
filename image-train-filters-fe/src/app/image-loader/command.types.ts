export class ItfCommand {
}

export class Setting {
    name: string;
    value: string;
}

export class Command {
    filter: string;
    settings: Setting[];
}

export class ItfChartizateCommand extends ItfCommand {
    bgColor: string;
    density: number;
    range: number;
    font: string;
    fontSize: number;
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