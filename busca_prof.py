PROF_ENTRADA = 0
PROF_SAIDA = 1
PAIS = 2

PE = 0
PS = 1


# Um grafo sera representado por listas 
# de adjacencias sobre um array em enderacemento
# direto, isto eh, a posicao j do array
# contera a lista de vizinhos do vertice j.
#
# Vertices sao numeros inteiros de 1 a n.


def busca_prof(g):
    #-----------
    def dfs(g, r, prof_entrada, prof_saida, 
            pais, profundidades):
        
        # visite r
        prof_entrada[r] = profundidades[PE]
        profundidades[PE] += 1

        # itere pelos vizinhos
        for w in g[r]:
            if pais[w] is None:
                pais[w] = r
                dfs(g, w, prof_entrada, prof_saida,
                    pais, profundidades)
                # visite aresta de arvore r,w
            elif prof_saida[w] is None and \
                 pais[r] != w:
                # visite aresta de retorno r,w
                pass
        
        prof_saida[r] = profundidades[PS]
        profundidades[PS] += 1
    #-----------
    
    n = len(g) - 1
    prof_entrada = [None] * (n+1)
    prof_saida = [None] * (n+1)
    pais = [None] * (n+1)
    profundidades = [1, 1]

    # escolha arbitraria do vertice 1 como raiz
    pais[1] = 1
    dfs(g, 1, prof_entrada, prof_saida, 
        pais, profundidades)

    return (prof_entrada, prof_saida, pais)


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

resultado_busca = busca_prof(grafo)

print resultado_busca[PROF_ENTRADA]
print resultado_busca[PROF_SAIDA]
print resultado_busca[PAIS]  



