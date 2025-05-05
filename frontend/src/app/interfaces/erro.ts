export class Erro {
    private message: string;
    private path: string;
    private status: string;
    private statusNumber: number;

    constructor(error: any) {
        this.message = error.error.message;
        this.path = error.error.path;
        this.status = error.error.status;
        this.statusNumber = error.error.statusNumber;
    }

    exibirDetalhes(): string {
        return `${this.message} 
            <br/>caminho: ${this.path} 
            <br/>código: ${this.statusNumber} 
            <br/>tipo: ${this.status}`
    }

    getMessage(): string {
        return this.message;
    }

    getPath(): string {
        return this.path;
    }

    getStatus(): string {
        return this.status;
    }

    getStatusNumber(): number {
        return this.statusNumber;
    }
}