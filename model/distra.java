public void caminho() {
        int start = 0;
        vertices[start].setVisitado(true);
        nTree = 0;
        for (int j = 0; j < tamAtual; j++) {
            int peso = matriz[start][j];
            path[j] = new Aresta(start, peso);
        }
        int minDist = path[getMin()].getPeso();
        while (nTree < tamAtual && minDist != infinito) {
            nTree++;
            int indexMin = getMin();
            minDist = path[indexMin].getPeso();
            if (minDist != infinito) {
                currentVert = indexMin;
                startToCurrent = path[indexMin].getPeso();
                vertices[currentVert].setVisitado(true);
                ajustarPath();
            }
        }
        //IMPRIME
        for (int i = 1; i < tamAtual; i++) {
            System.out.print(vertices[i].getNome() + ":");
            if (path[i].getPeso() == infinito) {
                System.out.print("inf");
            } else {
                System.out.print(" " + path[i].getPeso() + "(" + vertices[path[i].getDestino()].getNome() + ")");
            }
            System.out.println("");
        }

    }

    public int getMin() {
        int min = infinito;
        int posicao = 0;
        for (int i = 0; i < tamAtual; i++) {
            if (!vertices[i].isVisitado() && path[i].getPeso() < min) {
                min = path[i].getPeso();
                posicao = i;
            }
        }
        return posicao;
    }

    public void ajustarPath() {
        int coluna = 0;
        while (coluna < tamAtual) {
            if (!vertices[coluna].isVisitado()) {
                int current = matriz[currentVert][coluna];
                int start = current + startToCurrent;
                int sPathDist = path[coluna].getPeso();
                if (start < sPathDist) {
                    path[coluna].setDestino(currentVert);
                    path[coluna].setPeso(start);
                }
            }
            coluna++;
        }
    }