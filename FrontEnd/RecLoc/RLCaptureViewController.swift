//
//  RLCaptureViewController.swift
//  RecLoc
//
//  Created by Paril Shah on 3/20/17.
//  Copyright © 2017 Paril Shah. All rights reserved.
//

import UIKit
import Photos
import AVFoundation

class RLCaptureViewController: UIViewController, AVCapturePhotoCaptureDelegate, UIImagePickerControllerDelegate, UINavigationControllerDelegate{
    @IBOutlet var captureView: UIView!
    @IBOutlet var segment: UISegmentedControl!
    @IBOutlet var previewView: UIView!
    @IBOutlet var captureImageView: UIImageView!
    @IBOutlet var nextBarButton: UIBarButtonItem!
    
    var isFromGallery : Bool = false
    var captureSesssion : AVCaptureSession!
    var cameraOutput : AVCapturePhotoOutput!
    var previewLayer : AVCaptureVideoPreviewLayer!
    var locationFromGalleryImage : CLLocationCoordinate2D?
    let picker = UIImagePickerController()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(true)
        // Do any additional setup after loading the view.
        prepareCameraController()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    //MARK: - UIImagePickerControllerDelegate
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [String : Any]){
        
        let chosenImage = info[UIImagePickerControllerOriginalImage] as! UIImage //2
        captureImageView.contentMode = .scaleAspectFit //3
        captureImageView.image = chosenImage //4
        
        if let URL = info[UIImagePickerControllerReferenceURL] as? URL {
            let opts = PHFetchOptions()
            opts.fetchLimit = 1
            let assets = PHAsset.fetchAssets(withALAssetURLs: [URL], options: opts)
            let asset = assets.firstObject
            let location = asset!.location?.coordinate
            print(location ?? CLLocationCoordinate2D())
            self.locationFromGalleryImage = location
        }
        dismiss(animated:true, completion: nil) //5
        self.isFromGallery = true
        pressNext()
    }
    
    func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
        dismiss(animated: true, completion: nil)
    }
    
    // MARK: Custom Methods
    func prepareGallery(){
        captureView.alpha = 0
        previewView.alpha = 0
        picker.delegate = self
        picker.allowsEditing = false
        picker.sourceType = .savedPhotosAlbum
        picker.mediaTypes = UIImagePickerController.availableMediaTypes(for: .savedPhotosAlbum)!
        present(picker, animated: true, completion: nil)
    }
    
    @IBAction func changePhotoSource(){
        if segment.selectedSegmentIndex == 0{
            prepareCameraController()
        }else{
            prepareGallery()
        }
    }

    @IBAction func pressNext(){
        //photoSubmit
        let yourVC = self.storyboard?.instantiateViewController(withIdentifier: "RLPhotoEditViewController") as! RLPhotoEditViewController
        yourVC.placeImage = self.captureImageView.image
        yourVC.locationFromGalleryImage = self.locationFromGalleryImage
        yourVC.isFromGallery = self.isFromGallery
        self.navigationController?.pushViewController(yourVC, animated: true)
    }
    
}

extension RLCaptureViewController{
    func prepareCameraController(){
        // Prepare all camera related work.
        captureView.alpha = 1
        previewView.alpha = 1
        captureSesssion = AVCaptureSession()
        captureSesssion.sessionPreset = AVCaptureSessionPresetPhoto
        cameraOutput = AVCapturePhotoOutput()
        
        let device = AVCaptureDevice.defaultDevice(withMediaType: AVMediaTypeVideo)
        
        if let input = try? AVCaptureDeviceInput(device: device) {
            if (captureSesssion.canAddInput(input)) {
                captureSesssion.addInput(input)
                if (captureSesssion.canAddOutput(cameraOutput)) {
                    captureSesssion.addOutput(cameraOutput)
                    previewLayer = AVCaptureVideoPreviewLayer(session: captureSesssion)
                    previewLayer.frame = previewView.bounds
                    previewView.layer.addSublayer(previewLayer)
                    captureSesssion.startRunning()
                }
            } else {
                print("issue here : captureSesssion.canAddInput")
            }
        } else {
            print("some problem here")
        }
    }
    
    
    // This method you can use somewhere you need to know camera permission state
    func askPermission() {
        let cameraPermissionStatus =  AVCaptureDevice.authorizationStatus(forMediaType: AVMediaTypeVideo)
        switch cameraPermissionStatus {
        case .authorized:
            print("Already Authorized")
            
        case .denied:
            print("denied")
            let alert = UIAlertController(title: "Sorry :(" , message: "But  could you please grant permission for camera within device settings",  preferredStyle: .alert)
            let action = UIAlertAction(title: "Ok", style: .cancel,  handler: nil)
            alert.addAction(action)
            present(alert, animated: true, completion: nil)
            
        case .restricted:
            print("restricted")
            
        default:
            AVCaptureDevice.requestAccess(forMediaType: AVMediaTypeVideo, completionHandler: {
                [weak self]
                (granted :Bool) -> Void in
                
                if granted == true {
                    // User granted
                    print("User granted")
                    DispatchQueue.main.async(){
                        //Do smth that you need in main thread
                    }
                }
                else {
                    // User Rejected
                    print("User Rejected")
                    DispatchQueue.main.async(){
                        let alert = UIAlertController(title: "Error" , message:  "Camera it is the main feature of our application", preferredStyle: .alert)
                        let action = UIAlertAction(title: "Ok", style: .cancel, handler: nil)
                        alert.addAction(action)
                        self?.present(alert, animated: true, completion: nil)
                    }
                }
            });
        }
    }

    //MARK: AVCapturePhotoCaptureDelegate
    func capture(_ captureOutput: AVCapturePhotoOutput,  didFinishProcessingPhotoSampleBuffer photoSampleBuffer: CMSampleBuffer?,  previewPhotoSampleBuffer: CMSampleBuffer?, resolvedSettings:  AVCaptureResolvedPhotoSettings, bracketSettings:   AVCaptureBracketedStillImageSettings?, error: Error?){
        
        if let error = error {
            print("error occure : \(error.localizedDescription)")
        }
        
        if  let sampleBuffer = photoSampleBuffer,
            let previewBuffer = previewPhotoSampleBuffer,
            let dataImage =  AVCapturePhotoOutput.jpegPhotoDataRepresentation(forJPEGSampleBuffer:  sampleBuffer, previewPhotoSampleBuffer: previewBuffer) {
            
            print(UIImage(data: dataImage)?.size as Any)
            
            let dataProvider = CGDataProvider(data: dataImage as CFData)
            let cgImageRef: CGImage! = CGImage(jpegDataProviderSource: dataProvider!, decode: nil, shouldInterpolate: true, intent: .defaultIntent)
            let image = UIImage(cgImage: cgImageRef, scale: 1.0, orientation: UIImageOrientation.right)
            
            self.captureImageView.image = image
            self.isFromGallery = false
            pressNext()
        } else {
            print("some error here")
        }
    }
    
    @IBAction func didTakePhoto(){
        let settings = AVCapturePhotoSettings()
        let previewPixelType = settings.availablePreviewPhotoPixelFormatTypes.first!
        let previewFormat = [
            kCVPixelBufferPixelFormatTypeKey as String: previewPixelType,
            kCVPixelBufferWidthKey as String: 160,
            kCVPixelBufferHeightKey as String: 160
        ]
        settings.previewPhotoFormat = previewFormat
        cameraOutput.capturePhoto(with: settings, delegate:self)
    }

}
