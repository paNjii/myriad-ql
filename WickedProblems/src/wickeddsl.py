from pyparsing import *

class WickedDSL:
    '''
        Grammar definitions
    '''
    colon = Suppress(':')
    equals = '='
    word = Word(alphas)
    field_type = oneOf('boolean money')
    page_type = oneOf('page')
    stylesheet_type = oneOf('stylesheet')
    form_type = oneOf('form')
    evaluation = 'if'
    condition_unquoted = QuotedString(quoteChar="(", endQuoteChar=")", escChar='\\')
    condition_quoted = QuotedString(quoteChar="(", endQuoteChar=")", escChar='\\', unquoteResults=False)

    codeblock_unquoted = QuotedString(quoteChar="{", endQuoteChar="}", escChar='\\')
    codeblock_quoted = QuotedString(quoteChar="{", endQuoteChar="}", escChar='\\', unquoteResults=False)

    name = word
    field_display = QuotedString('"')

    question = field_display + name + colon + field_type
    evaluate = evaluation + condition_unquoted + codeblock_quoted
    statement = field_display + name + colon + field_type + equals + condition_quoted

    page = page_type + name + codeblock_unquoted

    field = statement | question | evaluate

    form_outer = form_type + name + codeblock_unquoted
    form_inner = OneOrMore(field)

    stylesheet = stylesheet_type + name + OneOrMore(page)

    def loadFile(ql_file):
        # open the file as a list of strings
        with open(ql_file, 'r') as ql_file_stream:
            __ql_content = ql_file_stream.readlines()
        # newlines and such
        __ql_content = [x.strip() for x in __ql_content]

        # squash the list into a string and return
        __ql_content =  ' '.join(__ql_content)

        # Do the initial format to make sure all blocks can be extracted
        if(__ql_content.count('\}') > 0):
            raise Exception("Illegal token \"}\"")

        return __ql_content
