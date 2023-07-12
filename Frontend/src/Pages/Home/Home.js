import React,{useEffect,useState} from 'react'
import { Redirect,useHistory,Link } from 'react-router-dom';
import {
Grid,
CircularProgress,
Button,
TextField,
Checkbox,
FormControlLabel,
FormControl,
Select,
InputLabel,
MenuItem,
Snackbar,
Typography,
CardContent,
CardActions,
Card
} from "@material-ui/core";
import Lottie from 'react-lottie';
import './Home.css'

function Home() {
      const defaultOptionsCar = {
          loop: true,
          autoplay: true,
          
          rendererSettings: {
          preserveAspectRatio: 'xMidYMid slice'
      }
      };
      const defaultOptionsInf = {
              loop: true,
              autoplay: true,
              
              rendererSettings: {
              preserveAspectRatio: 'xMidYMid slice'
              }
      };
      const history = useHistory()
      const [authState, setAuthState] = useState(false);
      const [userInfo, setUserInfo] = useState(null);


      return (
    <body>
      <section class="gradient-custom-2 text-dark  text-center">
      
       
       <div className=' text-light text-center py-5 h-100'>
        <div class="container">
          <div class="row d-flex justify-content-center align-items-center">
           <div class="col-xl-10">
    
            <div class=" justify-content-between align-items-center">
            <div >
                <h1><span class="text-white">Welcome to</span></h1>
                <h1><span class="text-white">Store and Employee Management Portal</span></h1>
                <p class="lead my-4">
                    Page for Add, Update and Approve Details</p>
                  
                       </div>
            </div>
            </div>
            </div>
            </div>        
      </div>
      </section>
      </body>
      
      )
}

export default Home