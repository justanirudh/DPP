#include <regex.h>        
#include<stdio.h>
#include<stdlib.h>
int main(){
regex_t regex;
int reti;
char msgbuf[100];

/* Compile regular expression */

//reti = regcomp(&regex,"([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]", 1);
reti = regcomp(&regex, "^Subject:[[:blank:]][CDX][,][[:alnum:][:space:]]{10}[,](0[1-9]|1[012])[/](0[1-9]|[12][0-9]|3[01])[/](19|20)[[:digit:]][[:digit:]][,]([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9][,][[:alnum:][:space:]]{10}", 1);

if (reti) {
    fprintf(stderr, "Could not compile regex\n");
    exit(1);
}

/* Execute regular expression */
reti = regexec(&regex, "Subject: C,Meeting   ,01/12/2017,15:30,NEB202    ", 0, NULL, 0);
//reti = regexec(&regex, "Subject: C,Meeting   ,01/12/2017,15:30", 0, NULL, 0);
//reti = regexec(&regex, "15:30", 0, NULL, 0);

if (!reti) {
    puts("Match");
}
else if (reti == REG_NOMATCH) {
    puts("No match");
}
else {
    regerror(reti, &regex, msgbuf, sizeof(msgbuf));
    fprintf(stderr, "Regex match failed: %s\n", msgbuf);
    exit(1);
}

/* Free memory allocated to the pattern buffer by regcomp() */
regfree(&regex);
return 0;
}
