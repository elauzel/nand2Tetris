load Main.vm,
output-file Main.out,
//compare-to Main.cmp,
output-list RAM[8000]%D2.6.2
RAM[8016]%B0.1.0
RAM[8015]%B0.1.0
RAM[8014]%B0.1.0
RAM[8013]%B0.1.0
RAM[8012]%B0.1.0
RAM[8011]%B0.1.0
RAM[8010]%B0.1.0
RAM[8009]%B0.1.0
RAM[8008]%B0.1.0
RAM[8007]%B0.1.0
RAM[8006]%B0.1.0
RAM[8005]%B0.1.0
RAM[8004]%B0.1.0
RAM[8003]%B0.1.0
RAM[8002]%B0.1.0
RAM[8001]%B0.1.0;

set RAM[8000] 12345, output;
repeat 10000 { vmstep; } output;