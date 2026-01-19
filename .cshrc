#! /bin/csh
# - load central setup (see this file for common setup bits)
source /cs/local/share/cshrc.common
    
# if this is an interactive shell, set up the terminal
if (${?interactive_shell} == 1) then
    stty echo echoe intr '^c' erase '^?' kill '^u'  
endif

# The default umask creates files which are not readable by group or others
# Uncomment the following to make created files readable by group or others
#umask 022

setenv PATH $HOME/bin:/cs/local/bin:/cs/fac/bin:${SYSPATH}:.
setenv MANPATH $HOME/man:/cs/local/man:/cs/local/share/man:/cs/fac/man:${SYSMANPATH}

#alias ls 'ls -FC \!*'	# turn on filetype and multicolumn output for ls
# The following set of aliases for cd, pushd, popd, and setXwd are 
# also optional.  They display the current working directory in 
# the title bar of an xterm.
if ( ${?DISPLAY} == 1 && ${?interactive_shell} == 1 ) then
    alias cd      'cd \!* ; setXwd'
    alias pushd   'pushd \!* ; setXwd'
    alias popd    'popd ; setXwd'
    alias setXwd  '/cs/local/bin/setXtermTitle "${HOST}:`pwd`"'
    setXwd
endif
