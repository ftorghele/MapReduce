# Algor Task 7

## Transpose Dataset:

```
awk 'BEGIN {FS=OFS=","}
{
for (i=1;i<=NF;i++)
{
 arr[NR,i]=$i;
 if(big <= NF)
  big=NF;
 }
}

END {
  for(i=1;i<=big;i++)
   {
    for(j=1;j<=NR;j++)
    {
     printf("%s%s",arr[j,i], (j==NR ? "" : OFS));
    }
    print "";
   }
}' input.csv  > output.csv

```
