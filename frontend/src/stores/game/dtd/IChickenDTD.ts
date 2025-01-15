import type { IItemDTD } from '@/stores/game/dtd/IItemDTD'
import type { IEggDTD } from './IEggDTD';

export interface IChickenDTD {
  chickenID: string;
  //gameId: string;
  //currentCalorie?: number | undefined;
  posX: number;
  posY: number;
  angle: number;
  egg:IEggDTD;
}
