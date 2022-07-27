import '../../styles/globals.css'
import Head from 'next/head'
import Header from '../../components/Header'
import Footer from '../../components/Footer'
function MyApp({ Component, pageProps }) {
  return (
    <>
      <Head>
       <title> Daiana | dev</title>
      </Head>
      <Header/>
        <Component {...pageProps} />
      <Footer />  
    </>
  )
}

export default MyApp
