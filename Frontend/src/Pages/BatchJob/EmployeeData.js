import {React,useState} from 'react'
import axios from 'axios'
import { useEffect} from 'react'
import Alert from '@material-ui/lab/Alert'
import {Snackbar} from "@material-ui/core"

function EmployeeData() {
  const [csvFile,SetCsvFile]=useState();

  const [snackbarMessage,setSnackBarMessage]=useState()
  const [snackbarSeverity,setSnackbarSeverity]=useState()
  const [showSnackbar,setSnackbar]=useState()
  const [role,setRole]=useState()

  const handleCloseSnackbar=(event,reason)=>{
          setSnackBarMessage('')
          setSnackbarSeverity('')
          setSnackbar(false)
  };
  
  

  const handleSubmit=(e)=>{
    e.preventDefault()
    const formData=new FormData();
    formData.append("name",csvFile.name)
    formData.append("file",csvFile)
    
    axios.post('http://localhost:8010/LoadEmployee/EmployeeData',formData)
    .then((res)=>{
      setSnackBarMessage('File Uploaded Sucessfully')
      setSnackbarSeverity('success')
      setSnackbar(true)
    })
    .catch((err)=>{
      setSnackBarMessage('File not Uploaded')
      setSnackbarSeverity('error')
      setSnackbar(true)
    })
  }

  useEffect(()=>{
    setRole(localStorage.getItem("roles")==='"ADMIN"'?true:false)

})

  
  return (
    
  
    <section>
      {role==true&&<div>
        
    <div class="container py-5 h-100">
  <div class="row d-flex justify-content-center align-items-center h-100">
    <div class="col-xl-5">
      <div class="card rounded-3 text-black">
        <div class="row g-0">
            <div class="card-body p-md-5 mx-md-4">
              <h3 className='text-center'>Employee Upload</h3>
              <br/>
              <form  className='pd-5' onSubmit={handleSubmit}>
              <div class="form-outline mb-4 justify-center">
              <input type='file' accept=".csv" onChange={(e)=>SetCsvFile(e.target.files[0])} />
                </div>
                      
                      <br/>
                      <button type="submit" className='btn btn-success' >Submit</button>

    </form>

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
  </Snackbar></div>}
  </section>
  );
}

export default EmployeeData
