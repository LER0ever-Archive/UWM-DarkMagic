import re
import urllib.request

regex = re.compile(("([a-z0-9!#$%&'*+\/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+\/=?^_`"
                    "{|}~-]+)*(@|\sat\s)(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?(\.|"
                    "\sdot\s))+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)"))
def get_emails(s):
    return (email[0] for email in re.findall(regex, s) if not email[0].startswith('//'))

if __name__ == '__main__':
    response = urllib.request.urlopen('https://www.cs.wisc.edu/people/undergraduate-students')
    page_source = response.read()
    emaillist = ""
    for email in get_emails(str(page_source, 'utf-8')):
        emaillist += email + ", "
    print(emaillist)