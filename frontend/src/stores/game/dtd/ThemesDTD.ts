export interface IThemeDTD {
    ground: string;
    wall: string;
    character: {
        snackman: string;
        ghost: string;
    };
}

export interface IThemes {
    [key: string]: IThemeDTD; // der Schlüssel ist der Theme-Name
}
