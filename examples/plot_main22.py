# %%
from sage.all import Graph, matrix

# %%
def main():
    with open('examples/main22.txt') as f:
        adjacency = f.readlines()
    
    adjacency = [row.strip() for row in adjacency]
    adjacency = [row.split() for row in adjacency]
    adjacency = [[int(cell) for cell in row] for row in adjacency]
    adjacency = matrix(adjacency)
    
    tree = Graph(adjacency)
    P = tree.plot()
    P.save('examples/main22.png')

# %%
main()