#!/usr/bin/perl

use strict;

my %links;

while ( my $line = <> ) {
  while( $line =~ /\[\[(.+?)\]\]/g ) {
    next unless ( defined $1 );
    my ($dst) = split(/\|/, $1);
    $links{$dst} = 1 if ( $dst !~ /[#<>[\]|{}:]/ );
  }
}

foreach my $link ( keys %links ) {
  print "$link\n";
}
