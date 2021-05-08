#include <stdio.h>
#include <string.h>
#include <stdlib.h>

float Check(char w ,float num1, float num2);
int main() {
    int j=0;
    char a[101];
    float b[101]={0};
    char c[101];
    float d[101];
    gets(a);
    for(int i=0;i<strlen(a);i++){
        if(a[i] == ' '){
            continue;
        }
        else if (a[i]=='+' || a[i]=='-' || a[i]=='*' || a[i]=='/'){
            c[j]=a[i];
            j++;
        }
        else{
            d[j]=(float)atoi(&a[i]);
            float k=(float)atoi(&a[i]);
            while (k>=10){
                k/=10;
                i++;
            }
            j++;
        }
    }
    int t=0;
    for(int i=0;i<j;i++){
        if((c[i]=='*'||c[i]=='/' )&& b[0]==0){
            b[t]=Check(c[i], d[i-1], d[i+1]);
            c[i]=0;
            d[i-1]=0;
            d[i+1]=0;
            t++;
        }
        
        else if(c[i]=='*'||c[i]=='/'){
            
            if(d[i+1]==0){
                b[t]=Check(c[i],d[i-1], b[t-1]);
                c[i]=d[i-1]=d[i+1]=0;
                t++;
            }
            else if(d[i-1]==0){
                b[t]=Check(c[i],b[t-1],d[i+1]);
                c[i]=d[i-1]=d[i+1]=0;
                t++;
            }
            else{
                b[t]=Check(c[i],d[i-1], d[i+1])+b[t-1];
                c[i]=d[i+1]=d[i-1]=0;
                t++;
            }
        printf("%lf\n",b[t-1]);
        }
    }
    for(int i=0;i<j;i++){
        if((c[i]=='+'||c[i]=='-' )&& b[0]==0){
            b[t]=Check(c[i], d[i-1], d[i+1]);
            c[i]=0;
            d[i-1]=0;
            d[i+1]=0;
            t++;
        }
        else if(c[i]=='+'||c[i]=='-'){
            if(d[i+1]==0){
                b[t]=Check(c[i],d[i-1], b[t-1]);
                c[i]=d[i-1]=d[i+1]=0;
                t++;
            }
            else if(d[i-1]==0){
                b[t]=Check(c[i],b[t-1],d[i+1]);
                c[i]=d[i-1]=d[i+1]=0;
                t++;
            }
            else{
                b[t]=Check(c[i], d[i-1], d[i+1])+b[t-1];
                c[i]=d[i-1]=d[i+1]=0;
                t++;
            }
        }
    }
    printf("%lf\n",b[t-1]);
    return 0;
}
float Check(char w, float num1, float num2){
    if(w == '+') return num1+num2;
    else if(w == '-') return num1-num2;
    else if(w=='*') return num1*num2;
    else if(w=='/') return num1/num2;
    return 0;
}
