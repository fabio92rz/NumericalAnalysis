
THIS IS A DEVELOPMENT VERSION OF GNUPLOT 5.1 on Cygwin-x86_64 
PLEASE USE IT AT YOUR OWN RISK.


This distribution was obtained by the unixish way of compilation under
Cygwin
	bash
	cd "E:\usr\Tatsu\cyg32work\gnuplotcvs\build-release"
	../gnuplot/configure --prefix=/opt/gp10 --without-qt --with-tutorial --with-readline=builtin
	make
	make check
	make install

Binaries are stripped at making a package.


Binaries included:
------------------

* gnuplot.exe: The gnuplot executable for Cygwin with x11 and wxt terminal.
* gnuplot_x11.exe: The gnuplot driver for x11 for Cygwin.

Installation
------------
* Install gnuplot 5.0.0 first using the cygwin setup. 
  Then almost required runtime libraries are installed.

* Install libcaca, lua5.2, wxWidgets3.0, and GTK2 runtime libraries using the cygwin setup.

* For deetailed information of depedency runtime libraries, see
  "Cygcheck_list.txt"

* Unzip this package.  
  Copy "opt" directory into Cygwin directory, e.g. C:\cygwin\opt.
  Then, you should find gnuplot.exe in Cygwin's /opt/gp510/bin directory,
  and similarly for other files.

* make alias in the ~/.bashrc like
 alias gnuplot = /opt/gp510/bin/gnuplot
 If you have already other versions of gnuplot executables, please change alias name.

* Copy the x11 resource file 'Gnuplot' to /etc/defaults/etc/X11/app-defaults
  directory. The file 'Gnuplot' is located at 
  /opt/gp510/share/gnuplot/5.1/app-defaults 

* See 'help start', 'help environment' etc. for setting up your environment,
  if not already done for the usual gnuplot for Windows.

* No additional documentation is included. Please use the usual gnuplot 5.1.0
  Windows binary distribution package which contains complete set of
  README's, INSTALL's, demo/, docs/, etc.
  http://www.tatsuromatsuoka.com/gnuplot/Eng/winbin/

Running
-------

* Run X-server, for example
    - Cygwin-X (free, www.cygwin.com) minmum is xinit and xlaunch.
    - Any free, shareware or commercial, like Xming,...

* Set DISPLAY environmental variable to your machine (localhost)

* Set LANG environmental variable to your locale (fontconfig requires utf-8 encoding)

* Run gnuplot.exe and enjoy "set term wxt" with its multiple graph windows

Notes
-------

The wxt terminal is supported but copy button and save file button does not work.

This binary is build without linking the GNU readline in order to avoid the license issue
http://gnuplot.10905.n7.nabble.com/readline-to-gnuplot-exe-console-mode-for-windows-td11173.html

Note that when users build binaries of gnuplot from source by themselves, 
buiding gnuplot binaries with link with the GNU readline is not a problem.


On end of 2015-03 I noticed that the feature of Cygwin/X is changed.
See:
http://cygwin.1069669.n5.nabble.com/xterm-fails-after-update-cygwin-td117317.html

================================================================================

(Last ChangeLog date is 2016-05-09)
May 2016

  Dr. Tatsuro MATSUOKA at Nagoya University
  Associate Professor
  Department of Molecular Design and Engineering, 
  Gradudate School of Engineering.
   (Undergraduate : Department of Chemical Engineering.)
  gnuplot related Email : tmacchant3_atmark_yahoo.co.jp
