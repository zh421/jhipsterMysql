export class ChatInterfaceModelhat {
  public q: string[] = [];
  constructor() {
    this.q;
  }

  public collectArg(...args: string[]): void {
    this.pushQ(args);
  }

  public pushQ(args: any): void {
    this.q.push(args);
  }

  public returnMsg(): any {
    return this.q;
  }
}
