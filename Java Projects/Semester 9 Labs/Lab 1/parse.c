#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main (int argc, char **argv) {
    
    int cl; char *data;
    cl = atoi(getenv("CONTENT_LENGTH"));
    data = (char *) malloc (cl + 1);
    fgets(data, cl + 1, stdin);  data[cl] = '\0';

    printf("Content-Type: text/html\n\n");

    char val[3][50];
    char var[3][50];
    int col = 0;
	int col2 = 0;	

    char *sentence = data;
	char *sentence2 = malloc(sizeof(sentence));
	strcpy(sentence2, sentence);
    char* varTok;
	char* valTok;
    char delim[] = "=&";

	
    varTok = strtok(sentence, delim);


    for(col; varTok != NULL; col++) {
		strcpy(var[col], varTok);
		//printf("%s\n", varTok);
		varTok = strtok(NULL, delim);
		varTok = strtok(NULL, delim);
	}

	valTok = strtok(sentence2, delim);

	for(col2; valTok != NULL; col2++) {
		valTok = strtok(NULL, delim);
		strcpy(val[col2], valTok);
		//printf("%s\n", valTok);
		valTok = strtok(NULL, delim);
	}

    printf("<html>\n<head>\n<style>\ntable, th, td {\nborder: 1px solid black;\nborder-collapse: 	 collapse;\n}\n</style>\n</head>\n<body>\n");
  	printf("<table style='width:50%'>\n\n");
  	printf("<tr><th>Variable</th><th>Value</th></tr>");

int counter = 0;

for(counter; counter<3;counter++) {
      printf("<tr><td>%s</td>", var[counter]);
      printf("<td>%s</td></tr>", val[counter]);
  }


    return 0;
}
