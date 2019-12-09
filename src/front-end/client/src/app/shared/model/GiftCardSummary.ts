
export class GiftCardSummary{
  id: string;
  initialBalance: number;
  remainingBalance: number;

  constructor(id:string, initinalBalance:number, remainingBalance:number){
    this.id = id;
    this.initialBalance = initinalBalance;
    this.remainingBalance = remainingBalance;
  }
}
