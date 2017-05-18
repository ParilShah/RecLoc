//
//  RLAlbumCollectionViewController.swift
//  RecLoc
//
//  Created by Paril Shah on 5/5/17.
//  Copyright © 2017 Paril Shah. All rights reserved.
//

import UIKit
import DZNEmptyDataSet
import NVActivityIndicatorView

private let reuseIdentifier = "PlacesCell"

class RLAlbumCollectionViewController: UICollectionViewController,DZNEmptyDataSetSource, DZNEmptyDataSetDelegate {
    var items = [Any]()

    override func viewDidLoad() {
        super.viewDidLoad()
        // Uncomment the following line to preserve selection between presentations
         self.clearsSelectionOnViewWillAppear = false

        // Do any additional setup after loading the view.
        let size: CGFloat = (self.collectionView!.frame.size.width - 5) / 2.0
        let cellSize = CGSize(width:size, height:size)
        let layout = UICollectionViewFlowLayout()
        layout.scrollDirection = .vertical
        layout.itemSize = cellSize
        layout.sectionInset = UIEdgeInsets(top: 1, left: 1, bottom: 1, right: 1)
        layout.minimumLineSpacing = 1.0
        layout.minimumInteritemSpacing = 1.0
        self.collectionView?.setCollectionViewLayout(layout, animated: true)
        
        self.collectionView!.emptyDataSetSource = self
        self.collectionView!.emptyDataSetDelegate = self

        fetchUserLocations()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: CUSTOM METHODS
    func fetchUserLocations(){
        let points = CGPoint(x: UIScreen.main.bounds.width/2.0 - 25, y: UIScreen.main.bounds.height/2.0 - 25)
        let activityIndicator =  NVActivityIndicatorView(frame: CGRect(x: points.x, y: points.y, width: 50, height: 50), type: .ballScaleMultiple, color: UIColor.init(colorLiteralRed: 216.0/255.0, green: 67.0/255.0, blue: 21.0/255.0, alpha: 1.0), padding: NVActivityIndicatorView.DEFAULT_PADDING)
        self.view.addSubview(activityIndicator)
        activityIndicator.startAnimating()
        
        let rlPlacesVM = RLPlacesVM.init(urlString:Constant.baseURL+"user/fetchUserLocations", paramerts:nil, block:{(response:AnyObject)in
            self.items = response as! [Location]
            self.collectionView?.reloadData()
            activityIndicator.stopAnimating()
        })
        rlPlacesVM.fetchUserLocations()
    }
    
    // MARK: - DZNEmptyDataSetSource
    func title(forEmptyDataSet scrollView: UIScrollView!) -> NSAttributedString! {
        let text = "No Photos"
        let attributes = [
            NSFontAttributeName: UIFont.boldSystemFont(ofSize: 18.0)
        ]
        return NSAttributedString(string: text, attributes: attributes)
    }
    
    func description(forEmptyDataSet scrollView: UIScrollView!) -> NSAttributedString! {
        let text = "Get started by uploading a photo."
        let paragraph = NSMutableParagraphStyle()
        paragraph.lineBreakMode = .byWordWrapping
        paragraph.alignment = .center
        
        let attributes = [
            NSFontAttributeName: UIFont.systemFont(ofSize: 14.0),
            NSParagraphStyleAttributeName: paragraph
        ]
        
        return NSAttributedString(string: text, attributes: attributes)
    }
    
    func image(forEmptyDataSet scrollView: UIScrollView!) -> UIImage! {
        return UIImage(named: "placeholder_photo")
    }
    
    func backgroundColor(forEmptyDataSet scrollView: UIScrollView!) -> UIColor! {
        return UIColor.init(red: 236.0/255.0, green: 240.0/255.0, blue: 241.0/255.0, alpha: 1.0)
    }
    
    @IBAction func pressRefresh(){
        self.items.removeAll()
        fetchUserLocations()
    }
    
}

extension RLAlbumCollectionViewController {
    
    // MARK: UICollectionViewDataSource
    override func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    override func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return self.items.count
    }
    
    override func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier, for: indexPath) as! RLPlaceCollectionViewCell
        // Configure the cell
        var location = items[indexPath.row] as! Location
        cell.textLabel?.text = location.locationName
        let image: UIImage?
        if (location.locationPhoto == nil){
            image = convertDataToImage(withData: location)
            location.locationPhoto = image
            items[indexPath.row] = location
        }else{
            image = location.locationPhoto
        }
        cell.imageView?.image = image
        return cell
    }
    
    func convertDataToImage(withData location:Location) -> UIImage{
        var locationImage:UIImage?
        let locationImageDataString:String = location.photoBytes!
        let locationImageData = NSData(base64Encoded: locationImageDataString, options:NSData.Base64DecodingOptions.init(rawValue: 0))
        locationImage = UIImage(data:locationImageData! as Data,scale:1.0)!
        return locationImage!
    }

    // MARK: UICollectionViewDelegateFlowLayout
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let retval = CGSize(width:(self.collectionView?.frame.size.width)!/2, height:64);
        return retval;
    }
    
}
