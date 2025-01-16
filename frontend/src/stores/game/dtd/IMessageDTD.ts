import type { IGameDTD } from "./IGameDTD";
import type { IPlayerDTD } from "./IPlayerDTD";
import type { IPlayerPositionDTD } from "./IPlayerPositionDTD";

export interface IMessageDTD {
    type: string;
    feedback: IPlayerPositionDTD | IPlayerDTD[] | IGameDTD | string | IPlayerPositionDTD[];
    status: 'ok' | 'error';
    time: string;
    password?:string
  }
