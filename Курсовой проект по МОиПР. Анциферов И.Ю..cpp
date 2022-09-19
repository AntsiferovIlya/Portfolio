#include <windows.h>
#include <stdlib.h>
#include <stdfix.h>
#include <iostream>
using namespace std;

const int g = 7;
void Dijkstra(int G[g][g], int st){	
	int distance[g], count, index, i, u, m=st+1, int_min;
	bool visited[g];
	for (i=0; i<g; i++){
		distance[i]=int_min; 
		visited[i]=false;
	}
	distance[st]=0;
	for (count=0; count<g-1; count++){
		int max=int_min;
		for (i=0; i<g; i++)
		if (!visited[i] && distance[i]>=max){
			max=distance[i]; index=i;
		}
	u=index;
	visited[u]=true;
	for (i=0; i<g; i++)
		if (!visited[i] && G[u][i] && distance[u]!=int_min &&
		distance[u] + G[u][i] > distance[i])
		distance[i] = distance[u] + G[u][i];
	}
	cout << " Критические пути до вершин графа:" << endl;
	for (i=0; i<g; i++) 
	if (distance[i]!=int_min)
		cout<< "X" << m <<" > "<< "X" << i+1 <<" = " << distance[i] << endl;
	else cout<< "X" << m << " > " << "X" << i+1 << " = " << " Такого пути не существует! "<< endl;
}
int main(){
	
	setlocale(LC_ALL, "Russian");

	int start,  G[g][g]={ {0, 2, 3, 7, 0, 0, 0},
						  {0, 0, 0, 0, 0, 5, 0},
						  {0, 0, 0, 0, 5, 0, 0},
						  {0, 0, 0, 0, 10, 0, 0},
						  {0, 0, 0, 0, 0, 0, 5},
						  {0, 0, 0, 0, 0, 0, 13},
						  {0, 0, 0, 0, 0, 0, 0}};	
	cout << "Введите начальную вершину (источник): >> ";
	cin >> start;
	Dijkstra(G, start-1);
	system ("pause >> void");
}

