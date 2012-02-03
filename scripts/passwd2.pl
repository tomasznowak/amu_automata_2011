#!/usr/bin/perl
use utf8;
use warnings;


open(PASSWD, "<" ,"/etc/passwd") or die "NIE MOŻNA OTWORZYĆ PLIKU: $!";
while(<PASSWD>){
  chomp;
  my($a, $b, $c, $d, $osoba, $konto, $e) = split(/:/);
  if ($konto=/\/home\/students.*/){
    if ($osoba=/(\w+a)\s+.*/){
      push @tab, $1;
    }
  }
}
close PASSWD;
@tab = sort @tab;

my %counts = ();
for (@tab){
$counts{$_}++;
}
foreach my $keys (keys %counts){
push @duplikaty,"$counts{$keys} $keys";
}

@duplikaty = reverse sort @duplikaty;

if ($duplikaty[0] =~ m/\d+\s+(.*)/){
print "$1 \n";
}
