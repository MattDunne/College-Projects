#include <stdio.h>
#include <string.h>

int main(int argc,char **argv) {
FILE *fp; int hits;

fp = fopen("/usr/lib/cgi-bin/counter.file","r");
fscanf(fp,"%d", &hits); hits++;
fclose(fp);

fp = fopen("/usr/lib/cgi-bin/counter.file","w");
fprintf(fp,"%d", hits);
fclose(fp);

char digits[20];

int q;
char *prefix= "<img src = '/odo";
char *postfix= ".gif'>";


//printf("%d", hits);

sprintf(digits, "%d", hits);
for(q = 0; q < strlen(digits); q++){
printf("%s%c%s", prefix, digits[q], postfix);
}

return 0;
}
