import React, { useState } from 'react'
import axios from 'axios'
import { useHistory } from 'react-router-dom';
import './Login.css';
import {omit} from 'lodash'
import{
    Grid,
    Button,
    TextField,
    FormControlLabel,
    Snackbar,
    Card,
    CardContent,
    CardAction,
    MenuItem,
    FormControl,
    Typography
} from "@material-ui/core"
import MultiAlert from "@material-ui/lab/Alert"
import Alert from '@material-ui/lab/Alert'



function Login() {

    const history=useHistory()

    const [loginStatus,setLoginStatus]=useState(false)
    const [loginErrorMessage,setLoginErrorMessage]=useState(null)

    const [formData,setFormData]=useState({
        email:'',
        password:'',
    })

    const [loginForm, setLoginForm] = useState({
              emailValid: false,
              passwordValid: false,
            });
    const [error, setError] = useState({ email: undefined, password: undefined });
  
    

    const [snackbarMessage,setSnackBarMessage]=useState()
    const [snackbarSeverity,setSnackbarSeverity]=useState()
    const [showSnackbar,setSnackbar]=useState()

    const handleCloseSnackbar=(event,reason)=>{
            setSnackBarMessage('')
            setSnackbarSeverity('')
            setSnackbar(false)
    };

    const handleChange=(event)=>{
      
        setFormData({
            ...formData,
            [event.target.name]:event.target.value!==""?event.target.value : null

        })
    }

    const handleSubmit=(e)=>{
      e.preventDefault()
        
     
        const data={
            username:formData.email,
            password:formData.password,
        };
       
        const header={
            'Content-Type':'application/json'
        }
        axios.post('http://localhost:8008/authorization/login',data,{headers:header})
        .then((res)=>{
            console.log(res.data)
            setSnackBarMessage('Logging in Sucessfully')
            setSnackbarSeverity('success')
            setSnackbar(true)
           // localStorage.setItem('username',JSON.stringify(res.data.username))
            localStorage.setItem('token',JSON.stringify(res.data.jwtAuthTokenString))
            localStorage.setItem('username',JSON.stringify(res.data.username))
            localStorage.setItem('roles',JSON.stringify(res.data.roles[0]))
            
            /*localStorage.setItem('CurrentTime',JSON.stringify(res.data.serverCurrentTime))
            localStorage.setItem('ExpirationTime',JSON.stringify(res.data.tokenExpirationTime))
            console.log(localStorage.getItem("ExpirationTime"))
            console.log(localStorage.getItem("ExpirationTime")-localStorage.getItem("CurrentTime"))
            */
            history.push("/")

        }).catch((err)=>{
            console.log(err)
            setSnackBarMessage('Incorrect Credentials')
            setSnackbarSeverity('error')
            setSnackbar(true)
        })
    }

    const checkValidity = (e) => {
      
      switch (e.target.name) {
        case "email":
          let pattern = /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/;
          loginForm.emailValid = pattern.test(e.target.value);
          break;
        case "password":
          loginForm.passwordValid = e.target.value.length >= 6;
          break;
        default:
          break;
      }

      switch (e.target.name) {
        
        case "email":
                  if (!loginForm.emailValid) {
                    setError({
                            ...loginForm,
                            email: "Invalid Email Address"
                          })
                          setLoginStatus(false)
                    }
                    else{
                      setError(  {
                        ...loginForm,
                        email: ""
                      })
                      setLoginStatus(true)
                    }; 
                    break;
                 
      case "password":
              if (!loginForm.passwordValid){

              setError(  {
                ...loginForm,
                password: "Invalid Password"
              })

              setLoginStatus(false)}
              else{
                setError(  {
                  ...loginForm,
                   password: ""
                })

                setLoginStatus(true)
              }; 
              break;
        }
  }

  return (
    <section>
    <div>
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-xl-5 ">
        <div class="card rounded-3 text-dark">
          <div class="row g-0">
            
              <div class="card-body p-md-5 mx-md-4 ">
                <h3 className='text-center'>LOGIN</h3>
                <form onSubmit={handleSubmit} >
                {error.email && (
                        <div class="alert alert-danger">
                          <p>
                            {" "}
                            {error.email}
                          </p>
                        </div>
                      )}

{error.password && (
                        <div class="alert alert-danger">
                          <p>
                            {" "}
                            <strong> </strong> {error.password}
                          </p>
                        </div>
                      )}

                  <p className='text-center'>Please login to your account</p>
                  <div class="form-outline mb-4">
                  <TextField
                                name="email"
                                label="Email"
                                value={formData.email}
                                type="text"
                                onChange={(e)=>handleChange(e)}
                                variant="outlined"
                                fullWidth
                                onBlur={checkValidity}
                                required
                              />
                              
                                
                  </div>

                  <div class="form-outline mb-4">
                  <TextField
                                name="password"
                                label="Password"
                                value={formData.password}
                                type="password"
                                onChange={(e)=>handleChange(e)}
                                variant="outlined"
                                fullWidth
                                onBlur={checkValidity}
                                required
                                />       
                  </div>

                  <div class="text-center pt-1 mb-5 pb-1">
                  <button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" disabled={!loginStatus} type="submit">Log
                      in</button>
                 
                  </div>

                </form>

              </div>
            </div>
           
          </div>
        </div>
      </div>
    </div>
  </div>
  <Snackbar open={showSnackbar} autoHideDuration={6000} onClose={handleCloseSnackbar}>
            <Alert onClose={handleCloseSnackbar} severity={snackbarSeverity} sx={{width:'100%'}}>
                {snackbarMessage}
            </Alert>
        </Snackbar>
    </section>
  )
 }


export default Login
