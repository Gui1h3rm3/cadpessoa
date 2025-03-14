export interface Resposta {
    content: [{
        id: number;
        nome: string;
        telefone: string;
        nascimento: string;
        email: string;
    }];
    pageable: [{
        pageNumber: number;
        pageSize: number;
        sort: [{
            sorted: boolean;
            unsorted: boolean;
            empty: boolean;
        }];
        offset: number;
        paged: boolean;
        unpaged: boolean;
    }];
    totalPages: number;
    totalElements: number;
    last: boolean;
    first: boolean;
    size: number;
    number: number;
    sort: [{
        sorted: boolean;
        unsorted: boolean;
        empty: boolean;
    }];
    numberOfElements: number;
    empty: boolean;
}