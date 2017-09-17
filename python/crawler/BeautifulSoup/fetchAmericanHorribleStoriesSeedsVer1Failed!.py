from bs4 import BeautifulSoup
import requests
import json

##TODO
##那个种子不知道用js怎么生成的，无法模拟生成过程，因此抓取不到....
class myCrawler:
     
     def getHtmlText(self,url):
          try:
               header = {'user-agent':'Mozilla/5.0'}
               r = requests.get(url, headers=header)
               r.raise_for_status()
               r.encoding = r.apparent_encoding
               return r.text
          except:
               return ""

     def fetchNameAndLink(self,html,prefixurl):
          episodes = []
          hasAppended = []
          soup = BeautifulSoup(html,'html.parser')
          for epis in soup.find('tbody').find_all('tr'):
               num=int(epis.attrs['data-episode'])
               if(num in hasAppended):
                    break
               hasAppended.append(num)
               name=epis.find('td',attrs="movie_name").string
               link=epis.find('td',attrs="link").a.attrs['href']
               episodes.append({"num":num,"name":name,"link":prefixurl+link})     
          return episodes

     def fetchSeeds(self,episodes):
          seeds = []
          for epis in episodes:
               html = self.getHtmlText(epis['link'])
               soup = BeautifulSoup(html,'html.parser')
               seed = soup.find(id='link_text_span')
               print(seed)
               break
               seeds.append(seed)
          return seeds

     def writeToFile(self):
          pass

     def main(self):
          prefixurl = 'https://moviejie.com'
          baseurl = 'https://moviejie.com/movie/fede2a/'
          html = self.getHtmlText(baseurl)
          episodes = self.fetchNameAndLink(html,prefixurl)
          seeds = self.fetchSeeds(episodes)
          print(seeds)
     
c = myCrawler()
c.main()
