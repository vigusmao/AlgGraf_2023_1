PROFS_E = 0
PROFS_S = 1
PAIS = 2
PE = 3
PS = 4
LOWS = 5
DEMARCS = 6
ARTICS = 7


# Um grafo sera representado por listas 
# de adjacencias sobre um array em enderacemento
# direto, isto eh, a posicao j do array
# contera a lista de vizinhos do vertice j.
#
# Vertices sao numeros inteiros de 1 a n.


def localizar_articulacoes(g):
    #-----------
    def dfs(g, r, controle):
        
        prof_entrada[r] = controle[PE]
        controle[PE] += 1

        # itere pelos vizinhos
        for w in g[r]:
            if pais[w] is None:
                pais[w] = r
                dfs(g, w, controle)
                
                # visite aresta de arvore r,w:
                
                # 1. verifica se o filho eh demarc
                if controle[DEMARCS][w] and \
                    controle[PAIS][r] != r:
                    # r eh articulacao!
                    # (pois tem filho demarcador
                    #  e r nao eh a raiz)
                    controle[ARTICS][r] = True
                    
                # 2. tenta copiar o low do filho
                low_w = controle[LOWS][w]
                low_r = controle[LOWS][r]
                if controle[PROFS_E][low_w] < \
                   controle[PROFS_E][low_r]:
                    controle[PROFS_E][low_r] = low_w
                       
            elif prof_saida[w] is None and \
                 pais[r] != w:
                # visite aresta de retorno r,w:
                low_r = controle[LOWS][r]
                if controle[PROFS_E][w] < \
                   controle[PROFS_E][low_r]:
                    controle[LOWS][r] = w 
                
        # visite vertice r
        # sei que o low do r estah pronto,
        # vou ver se ele eh demarcador
        pai_r = controle[PAIS][r]
        if controle[LOWS][r] in [r, pai_r]:
            controle[DEMARCS][r] = True

        if pai_r == r:
            # r eh raiz, vou ver se eh articulacao
            cont_filhos_da_raiz = 0
            for v in range(1, n+1):
                if v != r and \
                   controle[PAIS][v] == r:
                    cont_filhos_da_raiz += 1
            if cont_filhos_da_raiz >= 2:         
                # raiz tem 2 ou mais filhos,
                # entao eh articulacao
                controle[ARTICS][r] = True

        prof_saida[r] = controle[PS]
        controle[PS] += 1
    #-----------
    
    n = len(g) - 1
    prof_entrada = [None] * (n+1)
    prof_saida = [None] * (n+1)
    pais = [None] * (n+1)
    low = list(range(n+1))
    demarcadores = [False] * (n+1)
    articulacoes = [False] * (n+1)
    
    controle = [prof_entrada,
                prof_saida,
                pais,
                1, # PE
                1, # PS
                low,
                demarcadores,
                articulacoes]

    # escolha arbitraria do vertice 1 como raiz
    pais[1] = 1
    dfs(g, 1, controle)

    resultado = []
    for v in range(1, n+1):
        if articulacoes[v]:
            resultado.append(v)
    
    return resultado


#######
# MAIN
#######

grafo = [None,
    [3, 4, 5],
    [5, 6],
    [1, 4, 7, 8],
    [1, 3, 5, 7],
    [1, 2, 4, 6],
    [2, 5],
    [3, 4, 9],
    [3],
    [7]] 

articulacoes = localizar_articulacoes(grafo)

print articulacoes



