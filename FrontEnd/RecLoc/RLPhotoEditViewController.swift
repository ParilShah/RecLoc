//
//  RLPhotoEditViewController.swift
//  RecLoc
//
//  Created by Paril Shah on 4/8/17.
//  Copyright © 2017 Paril Shah. All rights reserved.
//

import UIKit
import Sharaku
import CoreLocation

class RLPhotoEditViewController: UIViewController, SHViewControllerDelegate {
    public var placeImage:UIImage!
    var isFromGallery:Bool = false
    var nextButton: UIBarButtonItem!
    var locationFromGalleryImage : CLLocationCoordinate2D?
    @IBOutlet weak var placeImageView:UIImageView!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        nextButton = UIBarButtonItem.init(title: "Next", style: .plain, target: self, action: #selector(pressNext(Sender:)))
        self.navigationItem.rightBarButtonItem = nextButton
        placeImageView.image = placeImage
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: - Custom Methods
    @IBAction func editPlacePhoto(){
        let sampleImage = self.placeImageView.image
        let vc = SHViewController(image: sampleImage!)
        vc.delegate = self
        self.present(vc, animated:true, completion: nil)
    }
    
    @objc private func pressNext(Sender: Any?){
        //photoSubmit
        let yourVC = self.storyboard?.instantiateViewController(withIdentifier: "photoSubmit") as! RLPhotoSubmitViewController
        yourVC.placeImage = self.placeImageView.image
        yourVC.isFromGallery = self.isFromGallery
        self.navigationController?.pushViewController(yourVC, animated: true)
    }
    

    func shViewControllerImageDidFilter(image: UIImage) {
        // Filtered image will be returned here.
        self.placeImageView.image = image
        dismiss(animated: true, completion: nil)
    }
    
    func shViewControllerDidCancel() {
        // This will be called when you cancel filtering the image.
        dismiss(animated: true, completion: nil)
    }
}
