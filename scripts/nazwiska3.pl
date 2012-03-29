
if ($#ARGV < 0)
{
	die "Program uruchamiamy: perl nazwiska.pl <plik wejsciowy> <ewentualnie plik wyjsciowy>\n";
}

open(UCHWYTPLIKUWEJSCIOWEGO, $ARGV[0]) || die "Nie mozna otworzyc pliku '".$ARGV[0]."'\n";

%wynik = ();

while ($scalar = <UCHWYTPLIKUWEJSCIOWEGO>)
{

    if ($scalar =~ m/^[0-9]*\s*([A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]*)-/) 
  {
    if (exists $wynik{$1})
    {
           $wynik{$1}++; #zwiekszenie wartosci w elemencie tablicy wynik o kluczu $1 (pierwszy czlon nazwiska) o jeden
    }
    else
    {
      $wynik{$1} = 1;
    }
  }
}

close UCHWYTPLIKUWEJSCIOWEGO;

print "\nWypisanie wyniku:\n";

$doPliku = 0;

if ($#ARGV > 0)
{
		if (open(UCHWYTPLIKUWYJSCIOWEGO, '>', $ARGV[1]))
	{
		$doPliku = 1;
	}
	else
	{	
		print "Nie można otworzyc pliku '".$ARGV[1]."' - wynik zostnaie wyświetlony na konsoli.\n"
	}
}
else
{
	print "Wynik zostnaie wyświetlony na konsoli.\n"
}

 foreach $value (sort {$wynik{$b} cmp $wynik{$a} }
           keys %wynik)
{
	if ($doPliku == 1) 
	{
		print UCHWYTPLIKUWYJSCIOWEGO $value." ".$wynik{$value}."\n";
	}
	else
	{
		print $value." ".$wynik{$value}."\n";
	}		
}

if ($doPliku == 1) 
{
	close UCHWYTPLIKUWYJSCIOWEGO;
}
