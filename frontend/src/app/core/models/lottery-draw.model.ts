export interface LotteryDraw {
  id: number;
  name: string;
  drawDate: string;
}

export interface CreateLotteryDrawDTO {
  name: string;
  drawDate: string;
}
