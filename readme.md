# Twaiku

[See this project live at twaiku.net!](http://twaiku.net) 

## *Sometimes the most meaningful poetry is unintentional.*


Twaiku is a natural language processing web app that identifies accidental haikus from public tweets. It is able to reliably 
detect if it is possible to make a 5/7/5 haiku from the text in a tweet by checking the syllable counts of the words from 
the most common pronunciations in the [CMU pronunciation dictionary](http://www.speech.cs.cmu.edu/cgi-bin/cmudict). 
Haikus are then saved in a database, tweeted by a bot, and can then be served to a web page.

Twaiku was made using Spring Web MVC, Hibernate ORM, Bootstrap, twitter4j/Twitter's free APIs, and a bit of elbow grease. It 
was initially built as the final project for a [Grand Circus](http://grandcircus.co) bootcamp by [Tristan Mortimer](http://github.com/Qwuke), 
[Marcus Perez](https://github.com/MarcusJPerez), and [Matt Davis](https://github.com/md204879).

###### (if you find joy in the weird accidental poetry of twitter users and would like to see more updates/features, please give a :star: for motivation)