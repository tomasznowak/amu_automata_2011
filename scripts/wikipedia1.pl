#!/usr/bin/perl

use strict;

my %links;

while ( my $line = <> ) {
  while( $line =~ /(?<link>   \[\[(?<value>(?:[^\]]++|\][^\]])*+)\]\])
                   |(?<char>  .)/msxg ) {
    next if ( defined $+{'char'} );
    my ($dst) = split(/\|/, $+{'value'});
    $links{$dst} = 1 if ( $dst !~ /[#<>[\]|{}:]/ );
  }
}

foreach my $link ( keys %links ) {
  print "$link\n";
}
